package utilities;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.jayway.jsonpath.JsonPath;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Helper {
	public static File jsonfile;

	public static String splitDoubleQuote(String value) {
		String[] arr = value.split("\"");
		String result = arr[1].trim().toString();
		return result;
	}

	public static String nameDateTime() {
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_hh.mm.ss");
		String folderName = formatter.format(today);
		return folderName;
	}

	public static String nameDate() {
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String folderName = formatter.format(today);
		return folderName;
	}

	public static Date parseStringToDate(String dateString) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate;
		try {
			startDate = df.parse(dateString);
			return startDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[] splitSpecificSymbold(String value, String symbol) {
		String[] arr = value.split(symbol);
		return arr;
	}

	public static String getJsonValue(String value, String fieldJson) throws IOException {
		jsonfile = new File(
				System.getProperty("user.dir") + "/src/test/resources/testDataResources/Json " + value + ".js");
		String valueJson = JsonPath.read(jsonfile, "$." + fieldJson).toString();
		return valueJson;
	}

	public static MongoCollection<Document> connectMongoDB(String table) {
		int port_no = 49418;
		String auth_user = "admin", auth_pwd = "123456", host_name = "ds249418.mlab.com", db_name = "aws",
				db_col_name = table, encoded_pwd = "";
		try {
			encoded_pwd = URLEncoder.encode(auth_pwd, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			Log.error(ex.toString());
		}

		String client_url = "mongodb://" + auth_user + ":" + encoded_pwd + "@" + host_name + ":" + port_no + "/"
				+ db_name;
		MongoClientURI uri = new MongoClientURI(client_url);

		@SuppressWarnings("resource")
		MongoClient mongo_client = new MongoClient(uri);

		MongoDatabase db = mongo_client.getDatabase(db_name);

		MongoCollection<Document> coll = db.getCollection(db_col_name);
		Log.info("Fetching all documents from the collection");
		return coll;
	}

	public static MongoCollection<Document> connectAccountsInMongoDB() {
		MongoCollection<Document> coll = connectMongoDB("accounts");
		return coll;
	}

	public static void deleteAccountInMongoDB(MongoCollection<Document> coll, String firstName) {

		try {
			coll.deleteMany(new Document("firstName", firstName));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static MongoCollection<Document> connectContactsInMongoDB() {
		MongoCollection<Document> coll = connectMongoDB("contacts");
		return coll;
	}

	public static void deleteContactInMongoDB(MongoCollection<Document> coll, String firstName) {

		try {
			coll.deleteMany(new Document("firstName", firstName));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static MongoCollection<Document> connectProductAppInMongoDB() {
		MongoCollection<Document> coll = connectMongoDB("product_application");
		return coll;
	}

	public static void deleteProductInMongoDB(MongoCollection<Document> coll, String accName) {
		try {
			coll.deleteMany(new Document("accountName", accName));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public static String getCurrentDate() {
		Date date = new Date();
		String DATE_FORMAT = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);
	}

	public static String getCurrentDateInCalendar() {
		Date date = new Date();
		String DATE_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);
	}

	public static boolean compare2List(List<List<String>> listBefore, List<List<String>> listAfter) {
		try {
			boolean status = false;
			int totalBefore = listBefore.size();
			int totalAfter = listAfter.size();
			if (totalBefore > totalAfter) {
				int totalRatio = totalBefore - totalAfter;
				for (int i = 0; i < totalAfter; i++) {
					List<String> rowArrAfter = new ArrayList<String>();
					List<String> rowArrBefore = new ArrayList<String>();
					rowArrAfter = listAfter.get(i);
					rowArrBefore = listBefore.get(i + totalRatio);
					for (int j = 0; j < rowArrAfter.size(); j++) {
						if (rowArrAfter.get(j).equalsIgnoreCase(rowArrBefore.get(j))) {
							status = true;
						} else {
							status = false;
							Log.error("The data of remaining rows is not correct.");
						}
					}
				}
			} else if (totalBefore == totalAfter && totalAfter > 1) {
				Log.error("The first row is still there, it is not removed out of table.");
			} else
				Log.error("Some thing wrong with the table");
			return status;
		} catch (Exception ex) {
			return false;
		}
	}

	public static void DeleteAFile(String filename) {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/testDataResources";
		File file = new File(filePath + "/" + filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
				Log.info("File is created");
			} else {
				Log.info("File already exist");
				file.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void DeleteAFile(String filePath, String filename) {
		File file = new File(filePath + "/" + filename);
		if (file.exists()) {
			file.delete();
		}
	}

	public static boolean seeAFile(String filename) {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/testDataResources";
		File file = new File(filePath + "/" + filename);
		try {
			return file.exists();
		} catch (Exception ex) {
			return false;
		}
	}
}
