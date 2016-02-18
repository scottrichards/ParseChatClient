package bitwyze.parsechatclient;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by srichard on 2/17/16.
 */
public class ChatListAdapter extends ArrayAdapter<Message> {

    public ChatListAdapter(Context context, List<Message> messages) {
      //  super(context, android.R.layout.simple_list_item_1, messages);
        super(context, 0, messages);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = getItem(position);
        TextView tvChatText = (TextView)convertView.findViewById(R.id.tvChatItem);
        TextView tvUserText = (TextView)convertView.findViewById(R.id.tvUserName);
       // tvChatText.setText(message.getMessageText());
       // tvUserText.setText(message.getUserName());
        return super.getView(position, convertView, parent);
    }
}
