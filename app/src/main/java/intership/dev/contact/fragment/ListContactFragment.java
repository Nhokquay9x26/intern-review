package intership.dev.contact.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import intership.dev.contact.R;
import intership.dev.contact.adapter.ContactAdapter;
import intership.dev.contact.model.Contact;
import intership.dev.contact.view.ContactActivity;
import intership.dev.contact.widget.LoadMoreListView;

/**
 * Created by nhokquay9x26 on 7/22/15.
 * Fragment List contact, have one listview contact in this
 * fragment
 */
public class ListContactFragment extends Fragment {
    private LoadMoreListView lv_Contact;
    private static ArrayList<Contact> sContacts = new ArrayList<Contact>();
    private static ContactAdapter sAdapter;

    public static final int AVATAR[] = {R.drawable.ic_avt1, R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4,
            R.drawable.ic_avt1, R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4,
            R.drawable.ic_avt1, R.drawable.ic_avt2, R.drawable.ic_avt3, R.drawable.ic_avt4};
    public static final String NAME[] = {"Anh", "Mỹ", "Việt Nam", "Đài Loan", "Nhật", "Hàn Quốc", "Thụy Điển",
            "Nga", "Anh", "Mỹ", "Việt Nam", "Đài Loan"};

    public static final String DESCRIPTION[] = {"Hello", "Hello", "Xin Chào", "Hào hào", "Nhật", "Hàn Quốc",
            "Thụy Điển", "Nga", "Anh", "Mỹ", "Việt Nam", "Đài Loan"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ContactActivity.changeTitle("Contacts");
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_list_contact, container, false);
        initList(v);
        if (sContacts.size() == 0) {
            event();
        }
        lv_Contact.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadDataTask().execute();
            }
        });
        return v;
    }

    public void initList(View v) {
        lv_Contact = (LoadMoreListView) v.findViewById(R.id.lvContacts);
        sAdapter = new ContactAdapter(getActivity(), R.layout.item_list_contact, sContacts);
        lv_Contact.setAdapter(sAdapter);

        sAdapter.setOnEditClick(new ContactAdapter.onEditClick() {
            @Override
            public void onClick(View v, final int pos) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                EditFragment editFragment = new EditFragment(sContacts.get(pos));
                transaction.replace(R.id.frContent, editFragment);
                transaction.addToBackStack("edit");
                transaction.commit();

                editFragment.setOnClickSave(new EditFragment.onClickSave() {
                    @Override
                    public void onClick(Contact contact) {
                        sContacts.get(pos).setmNameContacts(contact.getmNameContacts());
                        sContacts.get(pos).setmDescription(contact.getmDescription());
                        sAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    /**
     * Set data to listview
     */

    public void event() {
        for (int i = 0; i < NAME.length; i++) {
            Contact contact = new Contact();
            contact.setmImgContacts(AVATAR[i]);
            contact.setmNameContacts(NAME[i]);
            contact.setmDescription(DESCRIPTION[i]);

            sContacts.add(contact);
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
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            // add Loadmore Item
            event();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            // We need notify the adapter that the data have been changed
            sAdapter.notifyDataSetChanged();

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
