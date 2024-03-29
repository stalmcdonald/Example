package com.cm.weatherwhatever;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherXMLParsing extends Activity implements OnClickListener {
	
	//pulling city and state and temp from the google api
	//string reference URL
	static final String baseURL = "http://www.google.com/ig/api?weather=";
	//text view will change for weather text
	 TextView tv;
	 EditText city, state;
	
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle icicle) {
           super.onCreate(icicle);
           
           setContentView(R.layout.weather);
           Button b = (Button)findViewById(R.id.bWeather);
           tv = (TextView)findViewById(R.id.tvWeather);
           city = (EditText)findViewById(R.id.etCity);
           state = (EditText)findViewById(R.id.etState);
           //set a button for onclicklistener
           b.setOnClickListener(this);
  
   }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		String c = city.getText().toString();
		String s = state.getText().toString();
		
		StringBuilder URL = new StringBuilder(baseURL);
		URL.append(c + "," + s);
		String fullUrl = URL.toString();
		try{
			URL website = new URL(fullUrl);
			//getting xmlreader to parse data
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			HandlingXMLStuff doingWork = new HandlingXMLStuff();
			xr.setContentHandler(doingWork);
			xr.parse(new InputSource(website.openStream()));
			String information = doingWork.getInformation();
			tv.setText(information);
		}catch (Exception e){
			tv.setText("error");
		}
	}
}