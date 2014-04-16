package com.tajchert.glassware.dance;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

public class LessonActivity extends Activity{

	private static final String LOG_TAG = "GLASS_DANCE";
	
	private List<Card> mCards = new ArrayList<Card>();
	private CardScrollView mCardScrollView;
	private ExampleCardScrollAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mCardScrollView = new CardScrollView(this);
		adapter = new ExampleCardScrollAdapter();
		mCardScrollView.setAdapter(adapter);
		mCardScrollView.activate();
		mCardScrollView.setOnItemClickListener(adapter);
		setContentView(mCardScrollView);
		mCards = new ArrayList<Card>();
		createCard("English Waltz", R.drawable.waltz, mCards);
		createCard("Vienna Waltz", R.drawable.waltz, mCards);
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onStop() {
		super.onStop();
	}


	private void createCard(String title, int image, List<Card> cardList) {
		
		Card card1 = new Card(this);
		card1.setText(title);
		card1.setImageLayout(Card.ImageLayout.FULL);
		card1.addImage(image);
		
		mCards.add(card1);
		
	}
	private class ExampleCardScrollAdapter extends CardScrollAdapter implements OnItemClickListener  {
		@Override
		public int getCount() {
			return mCards.size();
		}
		@Override
		public Object getItem(int position) {
			return mCards.get(position);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return mCards.get(position).getView();
		}
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Log.e(LOG_TAG, "CLICKED: " + position);
			Intent i = null;
			switch (position) {
				case 0:
					i = new Intent(LessonActivity.this, DanceService.class);
					break;
				case 1:
					break;

			}
			if(i != null){
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.putExtra("dance", position);
				Log.d(LOG_TAG, "putExtra: "+ position);
				startService(i);
			}
		}
		@Override
		public int getPosition(Object arg0) {
			return 0;
		}
	}

}
