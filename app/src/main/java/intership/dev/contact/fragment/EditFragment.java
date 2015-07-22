package intership.dev.contact.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import intership.dev.contact.R;
import intership.dev.contact.model.Contact;
import intership.dev.contact.view.ContactActivity;

/**
 * Created by nhokquay9x26 on 7/22/15.
 */
public class EditFragment extends Fragment {
    ImageView imgAvatar;
    EditText edtName, edtDescreption;
    Button btnSave, btnCancel;
    TextView tvName;
    Contact mContact;

    public interface onClickSave {
        void onClick(Contact contact);
    }

    public onClickSave mClickSave;

    public void setOnClickSave(onClickSave clicksave) {
        this.mClickSave = clicksave;
    }

    /**
     * constructor reciver a contact to show GUI
     *
     * @param contact
     */
    public EditFragment(Contact contact) {
        mContact = contact;
        ContactActivity.changeTitle("Infomation");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_contact, container, false);

        initEdit(v);
        return v;
    }

    public void initEdit(View v) {
        imgAvatar = (ImageView) v.findViewById(R.id.imgAvatar);
        edtName = (EditText) v.findViewById(R.id.edtName);
        tvName = (TextView) v.findViewById(R.id.tvName);
        edtDescreption = (EditText) v.findViewById(R.id.edtDescreption);
        btnSave = (Button) v.findViewById(R.id.btnSave);
        btnCancel = (Button) v.findViewById(R.id.btnCancel);

        imgAvatar.setImageResource(mContact.getmImgContacts());
        edtName.setText(mContact.getmNameContacts());
        tvName.setText(mContact.getmNameContacts());
        edtDescreption.setText(mContact.getmDescription());

    }

}