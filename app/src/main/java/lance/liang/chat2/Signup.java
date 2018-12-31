package lance.liang.chat2;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import java.util.concurrent.*;
import android.view.Window.*;
import java.io.*;
import android.util.*;
import android.content.*;
import com.lzy.okgo.callback.*;
import com.lzy.okgo.model.*;
import com.google.gson.*;

public class Signup extends Activity
{
	private Button btn;
	private EditText text_username;
	private EditText text_password;
	private EditText text_email;
	private AlertDialog dialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		setTheme(Config.get(this).settings.theme);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		btn = (Button)findViewById(R.id.signupButton_signup);
		text_username = (EditText)findViewById(R.id.signupEditText_username);
		text_password = (EditText)findViewById(R.id.signupEditText_password);
		text_email = (EditText)findViewById(R.id.signupEditText_email);
		
		btn.setOnClickListener(new Button.OnClickListener(){
				@Override
				public void onClick(View p1){
					AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
					builder.setMessage("Please wait...");
					builder.setCancelable(false);
					dialog = builder.create();
					dialog.show();
					ContentValues parames = new ContentValues();
					parames.put(Communication.USERNAME, text_username.getText().toString());
					parames.put(Communication.EMAIL, text_email.getText().toString());
					parames.put(Communication.PASSWORD, text_password.getText().toString());
					parames.put(Communication.NAME, text_username.getText().toString());
					Communication.getComm(Signup.this).post(Communication.SIGNUP, parames, 
						new StringCallback() {
							@Override
							public void onSuccess(Response<String> response) {
								dialog.hide();
								ResultData result = (new Gson()).fromJson(response.body().toString(), ResultData.class);
								if (result.code == 0)
								{
									AlertDialog.Builder build = new AlertDialog.Builder(Signup.this);
									build.setMessage("Sign up successfully.");
									build.setPositiveButton("OK", new AlertDialog.OnClickListener() {
											@Override
											public void onClick(DialogInterface p1, int p2) {
												Signup.this.finish();
											}
										});
									build.show();
								}
								else
								{
									AlertDialog.Builder build = new AlertDialog.Builder(Signup.this);
									build.setMessage(result.message + " (Code: " + result.code + ")");
									build.setPositiveButton("OK", null);
									build.show();
								}
							}
							@Override
							public void onError(Response<String> response) {
								dialog.hide();
								AlertDialog.Builder build = new AlertDialog.Builder(Signup.this);
								build.setMessage("Connection Errors.");
								build.setPositiveButton("OK", null);
								build.show();
							}
						});
				}
		});
	}

	@Override
	protected void onDestroy()
	{
		//dialog.dismiss();
		super.onDestroy();
	}
}

