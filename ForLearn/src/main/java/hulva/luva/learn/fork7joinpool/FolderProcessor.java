/**
 * 
 */
package hulva.luva.learn.fork7joinpool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月9日
 *
 */
public class FolderProcessor extends RecursiveTask<List<String>> {

	private static final long serialVersionUID = 2761202334526606132L;
	// 这个变量用来存储将要处理的目录的绝对路径
	private final String path;
	// 这个变量用来存储要查找的文件扩展名
	private final String extension;

	// 使用构造器来初始化变量
	public FolderProcessor(String path, String extension) {
		super();
		this.path = path;
		this.extension = extension;
	}

	@Override
	protected List<String> compute() {
		List<String> sList = new ArrayList<String>();
		List<FolderProcessor> tasks = new ArrayList<FolderProcessor>();
		File file = new File(path);
		File content[] = file.listFiles();
		if (content != null) {
			for (int i = 0; i < content.length; i++) {
				if (content[i].isDirectory()) {
					FolderProcessor task = new FolderProcessor(
							content[i].getAbsolutePath(), extension);
					task.fork();
					tasks.add(task);
				} else {
					if (checkFile(content[i].getName())) {
						sList.add(content[i].getAbsolutePath());
					}
				}

			}
		}
		if (tasks.size() > 50) {
			System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(),
					tasks.size());
		}
		addResultsFromTasks(sList, tasks);
		return sList;
	}

	private void addResultsFromTasks(List<String> sList,
			List<FolderProcessor> tasks) {
		for (FolderProcessor item : tasks) {
			sList.addAll(item.join());
		}
	}

	private boolean checkFile(String name) {
		return name.endsWith(extension);
	}
}
