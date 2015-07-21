package intership.dev.contact.View;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import intership.dev.contact.Adapter.ContactAdapter;
import intership.dev.contact.Model.Contact;
import intership.dev.contact.R;


public class ContactActivity extends Activity {

    ListView lv_Contact;
    ArrayList<Contact> mContacts;
    ContactAdapter adapter;
    public static final int AVATAR[] = {R.drawable.ic_avt1, R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4};
    public static final String NAME[] = {"Anh", "Mỹ", "Việt Nam", "Đài Loan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact);
        initList();
        event();
    }

    public void initList() {
        lv_Contact = (ListView) findViewById(R.id.lvContacts);
        mContacts = new ArrayList<Contact>();
        adapter = new ContactAdapter(this, R.layout.item_list_contact, mContacts);
        lv_Contact.setAdapter(adapter);
    }

    public void event() {
        for (int i = 0; i < NAME.length; i++){
            Contact contact = new Contact();
            contact.setmImgContacts(AVATAR[i]);
            contact.setmNameContacts(NAME[i]);

            mContacts.add(contact);
        }
    }

}
