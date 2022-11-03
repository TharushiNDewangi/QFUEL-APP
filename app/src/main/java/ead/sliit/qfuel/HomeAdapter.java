package ead.sliit.qfuel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ead.sliit.qfuel.model.StationOwner;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.UserAdapterVH> {

    private StationOwner userResponseob;
    private Context context;
    private HomeAdapter.ClickedItem clickedItem;

    public HomeAdapter(HomeAdapter.ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(StationOwner userResponseob) {
        this.userResponseob = userResponseob;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        //return new AdminHomeAdaptor.UserAdapterVH(LayoutInflater.from(context).inflate(R.layout.fuel_admin_adaptor,parent,false));
        return new HomeAdapter.UserAdapterVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.UserAdapterVH holder, int position) {

        StationOwner userResponse = userResponseob;

        String username = userResponse.getLocation();
        String start = userResponse.getStart();
        String type = userResponse.getType();
        String end = userResponse.getEnd();
        holder.username.setText(username);
        holder.start.setText(start);
        holder.type.setText(type);
        holder.end.setText(end);
//        holder.imageMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickedItem.ClickedUser(userResponse);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface ClickedItem{
        public void ClickedUser(StationOwner userResponse);
    }

//    @Override
//    public int getItemCount() {
//        return userResponseob.size();
//    }

    public class UserAdapterVH extends RecyclerView.ViewHolder {

        TextView username,start,type,end;
        TextView prefix;
        ImageView imageMore;

        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.searchadaptorlocation);
            start = itemView.findViewById(R.id.searchadaptorstart);
            type = itemView.findViewById(R.id.searchadaptortype);
            end = itemView.findViewById(R.id.searchadaptorend);

            imageMore = itemView.findViewById(R.id.imageMore);


        }
    }
}

