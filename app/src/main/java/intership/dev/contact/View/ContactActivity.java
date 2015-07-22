package intership.dev.contact.View;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import intership.dev.contact.Adapter.ContactAdapter;
import intership.dev.contact.widget.LoadMoreListView;
import intership.dev.contact.Model.Contact;
import intership.dev.contact.R;


public class ContactActivity extends Activity {

    LoadMoreListView lv_Contact;
    ArrayList<Contact> mContacts;
    ContactAdapter adapter;

    public static final int AVATAR[] = {R.drawable.ic_avt1, R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4,
            R.drawable.ic_avt1, R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4};
    public static final String NAME[] = {"Anh", "Mỹ", "Việt Nam", "Đài Loan", "Nhật", "Hàn Quốc", "Thụy Điển", "Nga"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact);
        initList();
        event();
    }

    public void initList() {
        lv_Contact = (LoadMoreListView) findViewById(R.id.lvContacts);
        mContacts = new ArrayList<Contact>();
        adapter = new ContactAdapter(this, R.layout.item_list_contact, mContacts);
        lv_Contact.setAdapter(adapter);
        lv_Contact.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.d("TAG", "Load");
                new LoadDataTask().execute();
            }
        });
    }

    public void event() {
        for (int i = 0; i < NAME.length; i++) {
            Contact contact = new Contact();
            contact.setmImgContacts(AVATAR[i]);
            contact.setmNameContacts(NAME[i]);

            mContacts.add(contact);
        }
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            if (isCancelled()) {
                return null;
            }

            // Simulates a background task
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            Log.d("TAG", "Load được");
            // add Loadmore Item
            for (int i = 0; i < NAME.length; i++) {
                Contact contact = new Contact();
                contact.setmImgContacts(AVATAR[i]);
                contact.setmNameContacts(NAME[i]);

                mContacts.add(contact);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            // We need notify the adapter that the data have been changed
            adapter.notifyDataSetChanged();

            // Call onLoadMoreComplete when the LoadMore task, has finished
            ((LoadMoreListView) lv_Contact).onLoadMoreComplete();

            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            // Notify the loading more operation has finished
            ((LoadMoreListView) lv_Contact).onLoadMoreComplete();
        }
    }

}
