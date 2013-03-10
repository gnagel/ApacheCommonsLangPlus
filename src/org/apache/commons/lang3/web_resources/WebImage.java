package org.apache.commons.lang3.web_resources;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.encryption.MD5String;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;


public final class WebImage {
	private static final String fileName(final String web_url) {
		return MD5String.encode(web_url) + ".png.dat";
	}


	public static Bitmap loadFromCache(final Context context, final String web_url) {
		try {
			final String fileName = fileName(web_url);
			final File directory = writableDirectory(context);
			final File file = new File(directory, fileName);

			if (!file.exists() || file.isDirectory()) {
				return null;
			}

			return BitmapFactory.decodeFile(directory.getPath() + "/" + fileName);
		}
		catch (final Throwable e) {
			return null;
		}
	}


	public static Bitmap loadFromWeb(final Context context, final String web_url) throws MalformedURLException,
			IOException {
		final String fileName = fileName(web_url);
		final File directory = writableDirectory(context);
		final File file = new File(directory, fileName);

		if (file.isDirectory()) {
			file.delete();
		}

		if (!file.exists()) {
			final URLConnection httpURLConnection = new URL(web_url).openConnection();
			httpURLConnection.setDoInput(true);
			httpURLConnection.connect();

			final Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());

			file.createNewFile();
			final FileOutputStream stream = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			stream.flush();
			stream.close();

			return bitmap;
		}

		Log.v(context.getPackageName(), directory.getPath() + "/" + fileName);
		return BitmapFactory.decodeFile(directory.getPath() + "/" + fileName);
	}


	public static File writableDirectory(final Context context) {
		File dir = null;

		dir = context.getCacheDir();
		if (dir.canWrite()) {
			return dir;
		}

		dir = context.getFilesDir();
		if (dir.canWrite()) {
			return dir;
		}

		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			final String[] path = new String[] { Environment.getExternalStorageDirectory().getAbsolutePath(), "Android", "data" };
			dir = new File(StringUtils.join(path, System.getProperty("file.separator")), context.getPackageName());
			dir.mkdirs();
			return dir;
		}

		dir = context.getCacheDir();
		if (dir.canWrite()) {
			return dir;
		}

		return context.getFilesDir();
	}
}
