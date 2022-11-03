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

public class AdminHomeAdaptor extends RecyclerView.Adapter<AdminHomeAdaptor.UserAdapterVH> {

    private List<StationOwner> userResponseList;
    private Context context;
    private ClickedItem clickedItem;
    ItemClickListener itemClickListener;
    int selectedPosition=-1;



    public AdminHomeAdaptor(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }
//    public AdminHomeAdaptor(ClickedItem clickedItem,ItemClickListener itemClickListener) {
//        this.clickedItem = clickedItem;
//        this.itemClickListener=itemClickListener;
//    }

    public void setData(List<StationOwner> userResponseList) {
        this.userResponseList = userResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
//        View view = LayoutInflater.from(mContext).inflate(R.layout.myview, parent, false);
//        view.setOnClickListener(mOnClickListener);
//        return new MyViewHolder(view);
        //return new AdminHomeAdaptor.UserAdapterVH(LayoutInflater.from(context).inflate(R.layout.fuel_admin_adaptor,parent,false));
        return new AdminHomeAdaptor.UserAdapterVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fuel_admin_adaptor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {

        StationOwner userResponse = userResponseList.get(position);

        String username = userResponse.getLocation();
        String start = userResponse.getStart();
        String type = userResponse.getType();
        String end = userResponse.getEnd();
        holder.username.setText(username);
        holder.start.setText(start);
        holder.type.setText(type);
        holder.end.setText(end);
        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedUser(userResponse);
            }
        });

    }

    public interface ClickedItem{
        public void ClickedUser(StationOwner userResponse);
    }

    @Override
    public int getItemCount() {
        return userResponseList.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder {

        TextView username,start,type,end;
        TextView prefix;
        ImageView imageMore;

        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.adminadaptorlocation);
            start = itemView.findViewById(R.id.adminadaptorstart);
            type = itemView.findViewById(R.id.adminadaptortype);
            end = itemView.findViewById(R.id.adminadaptorend);

            imageMore = itemView.findViewById(R.id.imageMore);


        }
    }
}

