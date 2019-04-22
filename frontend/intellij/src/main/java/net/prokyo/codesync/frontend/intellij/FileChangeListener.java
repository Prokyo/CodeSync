package net.prokyo.codesync.frontend.intellij;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.Topic;
import org.jetbrains.annotations.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class FileChangeListener implements ProjectComponent {

	private final Set<Project> indexedProjects = new HashSet<>();
	private final Set<VirtualFile> registeredFiles = new HashSet<>();

	@Override
	public void projectOpened() {
		final Topic<FileEditorManagerListener> fileOpenTopic = FileEditorManagerListener.FILE_EDITOR_MANAGER;

		// iterate through all opened projects
		Stream.of(ProjectManager.getInstance().getOpenProjects()).filter(this.indexedProjects::add).forEach(project -> {

			// add a listener for file
			project.getMessageBus().connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {

				@Override
				public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
					if(!FileChangeListener.this.registeredFiles.add(file))
						throw new IllegalStateException("A virtual file seems to be already registered: " + file);

					// get in memory document for real-time events
					Document document = FileDocumentManager.getInstance().getCachedDocument(file);
					if(document == null) return;

					document.addDocumentListener(new DocumentListener() {
						@Override public void beforeDocumentChange(DocumentEvent event) {}

						@Override
						public void documentChanged(DocumentEvent event) {
							System.out.println(event);
						}
					});
				}

				@Override
				public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
					FileChangeListener.this.registeredFiles.add(file);
				}

				@Override public void selectionChanged(@NotNull FileEditorManagerEvent event) {}
			});

		});
	}

	@Override
	public void projectClosed() {

	}

	@Override
	public void initComponent() {

	}

	@Override
	public void disposeComponent() {

	}

	@NotNull
	@Override
	public String getComponentName() {
		return "CodeSyncFileChangeListener";
	}
}
