package utilities;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateJsonFile {
	public static String urlGeneral = "";
	public static String url = "";
	public static String jsessID = "";

	public static void createJsonFileSQPage(String searchField, String value) throws Exception {
		haveToLoginForGettingSessionID();
		if (searchField.equalsIgnoreCase("Location Id")) {
			url = urlGeneral + "nbn/details?loc=" + value + "&getDetails=true&getRaw=true";
		} else if (searchField.equalsIgnoreCase("Telstra ID")) {
			url = urlGeneral + "aapt/qualifyProduct/" + value;
		} else if (searchField.equalsIgnoreCase("Report Date")) {
			url = urlGeneral + "report/retrieveReportsDates/ATLAS";
		} else if (searchField.equalsIgnoreCase("Report Type")) {
			url = urlGeneral + "retrieveReports/ATLAS/2018-4";
		} else {
			Log.error("The value " + value + " is not secified in Json configuration of Bureau Portal");
			throw new RuntimeException(
					"The value " + value + " is not secified in Json configuration of Bureau Portal");
		}
		String result = connectToWebServiceAfterLogin(url, "GET", null);
		Log.info(result);
		writeJson(result, value);
	}

	public static void createJsonFileBureauReportDatePage(String bureau, String date) throws Exception {
		haveToLoginForGettingSessionID();
		url = urlGeneral + "report/retrieveReports/" + bureau + "/" + date;
		String result = connectToWebServiceAfterLogin(url, "GET", null);
		Log.info(result);
		writeJson(result, date);
	}

	public static void createJsonFileGapPayments(String searchField, String value) throws Exception {
		haveToLoginForGettingSessionID();
		if (searchField.equalsIgnoreCase("Gap Payments")) {
			url = urlGeneral + "report/retrieveGap/" + value.toUpperCase();
		} else {
			Log.error("The value " + value + " is not secified in Json configuration of Bureau Portal");
			throw new RuntimeException(
					"The value " + value + " is not secified in Json configuration of Bureau Portal");
		}
		String result = connectToWebServiceAfterLogin(url, "GET", null);
		Log.info(result);
		writeJson(result, value);
	}

	public static void createJsonFileCINDYWithCIDN(int cidn) throws Exception {
		haveToLoginForGettingSessionID();
		url = urlGeneral + "account/cidn/detail?idCIDN=" + cidn;
		String result = connectToWebServiceAfterLogin(url, "GET", null);
		writeJson(result, "AccountService " + cidn);
	}

	public static void createJsonFileAllCINDY() throws Exception {
		haveToLoginForGettingSessionID();
		url = urlGeneral + "account/search?";
		String result = connectToWebServiceAfterLogin(url, "GET", null);
		writeJson(result, "AllAccountService");
	}

	public static void createJsonFileTabnameInCindy(String tabName, int cidn) throws Exception {
		haveToLoginForGettingSessionID();
		switch (tabName.toLowerCase()) {
		case ("nps"):
			url = urlGeneral + "cidn/service/nps?idCIDN=" + cidn;
			break;
		case ("salesforce"):
			url = urlGeneral + "cidn/service/salesforce?idCIDN=" + cidn;
			break;
		case ("notes & email"):
			url = urlGeneral + "cidn/service/notesAndEmails?idCIDN=" + cidn;
			break;
		case ("customer information"):
			url = urlGeneral + "cidn/service/customerInformation?idCIDN=" + cidn;
			break;
		case ("billing"):
			url = urlGeneral + "cidn/service/billing?idCIDN=" + cidn;
			break;
		case ("service numbers"):
			url = urlGeneral + "cidn/service/number?idCIDN=" + cidn;
			break;
		case ("contract details"):
			url = urlGeneral + "cidn/service/contract?idCIDN=" + cidn;
			break;
		case ("agreement details"):
			url = urlGeneral + "cidn/service/agreement?idCIDN=" + cidn;
			break;
		default:
			Log.error("The group " + tabName + " is not specified in Json configuration of Bureau Portal");
			throw new RuntimeException(
					"The group " + tabName + " is not specified in Json configuration of Bureau Portal");
		}
		String result = connectToWebServiceAfterLogin(url, "GET", null);
		Log.info(result);
		writeJsonAccSer(tabName, result, "" + cidn);
	}

	public static void createJsonFileSerNumPopup(String value) throws Exception {
		haveToLoginForGettingSessionID();
		url = urlGeneral + "cidn/service/number/detail?serviceNumberId=" + value;
		String result = connectToWebServiceAfterLogin(url, "GET", null);
		writeJsonAccSer("Service Number Details", result, "" + value);
	}

	public static void writeJson(String result, String value)
			throws FileNotFoundException, UnsupportedEncodingException {
		final String dir = System.getProperty("user.dir");
		PrintWriter writer = new PrintWriter(dir + "/Json " + value + ".js", "UTF-8");
		writer.println(result);
		writer.close();
	}

	public static void writeJsonAccSer(String tabName, String result, String value)
			throws FileNotFoundException, UnsupportedEncodingException {
		final String dir = System.getProperty("user.dir");
		PrintWriter writer = new PrintWriter(
				dir + "/src/test/resources/testDataResources/" + tabName + " " + value + ".js", "UTF-8");
		writer.println(result);
		writer.close();
	}

	public static void haveToLoginForGettingSessionID() throws IOException {
		urlGeneral = "http://10.3.2.10:9090/bureau-portal/api/v1/";
		String urlLogin = urlGeneral + "login";
		String urlParameters = "username=dev_bureau=atlas&password=BTBatlasdev2018";
		connectToWebServiceWithLoginNotJson(urlLogin, "POST", urlParameters);
	}

	public static String getCookieValue(HttpURLConnection con) {
		Map<String, List<String>> headerFields = con.getHeaderFields();
		Set<String> headerFieldsSet = headerFields.keySet();
		Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
		String cookieValue = "";
		while (hearerFieldsIter.hasNext()) {
			String headerFieldKey = hearerFieldsIter.next();
			if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {
				List<String> headerFieldValue = headerFields.get(headerFieldKey);
				for (String headerValue : headerFieldValue) {
					System.out.println("Cookie Found...");
					String[] fields = headerValue.split(";\\s*");
					cookieValue = fields[0];
					break;
				}
			}

		}
		return cookieValue;
	}

	public static String connectToWebServiceAfterLogin(String urlString, String method, String postBody)
			throws InterruptedException {
		String result = null;
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(urlString).openConnection();
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Cookie", jsessID);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod(method.toUpperCase());
			if (method.equalsIgnoreCase("POST")) {
				con.getOutputStream().write(postBody.getBytes());
				con.getOutputStream().flush();
			}
			con.connect();
			Thread.sleep(5000);
			if (con.getResponseCode() == 200 || con.getResponseCode() == 202 || con.getResponseCode() == 201) {
				result = readContentOfStream(con.getInputStream());
			} else {
				result = readContentOfStream(con.getErrorStream());
			}
		} catch (IOException e) {
		}
		return result;
	}

	private static String readContentOfStream(InputStream inputStream) {
		StringBuilder stringBuilder = new StringBuilder();
		byte[] readBuffer = new byte[4096];
		int read;
		try {
			while ((read = inputStream.read(readBuffer)) != -1) {
				stringBuilder.append(new String(readBuffer, 0, read));
			}
		} catch (IOException e) {
		}
		return stringBuilder.toString();
	}

	public static String connectToWebServiceWithLoginNotJson(String urlString, String method, String urlParameters) {
		String result = null;
		try {
			byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
			int postDataLength = postData.length;
			HttpURLConnection con = (HttpURLConnection) new URL(urlString).openConnection();
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod(method.toUpperCase());
			if (method.equalsIgnoreCase("POST")) {
				con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				con.setRequestProperty("charset", "utf-8");
				con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
				con.setUseCaches(false);
				try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
					wr.write(postData);
				}
			}
			con.connect();
			jsessID = getCookieValue(con);
			if (con.getResponseCode() == 200 || con.getResponseCode() == 202 || con.getResponseCode() == 201) {
				result = readContentOfStream(con.getInputStream());
			} else {
				result = readContentOfStream(con.getErrorStream());
			}
		} catch (IOException e) {
		}
		return result;
	}
}
