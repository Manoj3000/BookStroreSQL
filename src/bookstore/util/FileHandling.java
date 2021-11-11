package bookstore.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FileHandling<E> {

	private static String FILE_NAME;
	// create file
	private File file = null;
	private Gson gson = null;

	public FileHandling(String fileName) {
		FILE_NAME = fileName;
		file = new File(fileName);
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	// writing
	public void write(List<E> items) {
		String jsonString = gson.toJson(items);
//		System.out.println("Writing this " +  jsonString);
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(jsonString);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// reading
	public  List<E> read(Type type) {
		try {
			if (file.exists()) {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
				List<E> items = gson.fromJson(bufferedReader, type);
				return items;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
