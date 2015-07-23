package intership.dev.contact.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import intership.dev.contact.fragment.ListContactFragment;
import intership.dev.contact.R;
import intership.dev.contact.model.Contact;


public class ContactActivity extends Activity {
    private static TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact);
        tvTitle = (TextView) findViewById(R.id.tvHeaderContacts);
        AddListFragment();
    }

    public void AddListFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ListContactFragment listContactFragment = new ListContactFragment();
        transaction.replace(R.id.frContent, listContactFragment);
        transaction.addToBackStack("main");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            this.finish();
        } else {
            getFragmentManager().popBackStack();
            this.finish();
        }
    }

    /**
     * change title when change fragment
     * @param title: text for title
     */
    public static void changeTitle(String title) {
        tvTitle.setText(title);
    }
}
