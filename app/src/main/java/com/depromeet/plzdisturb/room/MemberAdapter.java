package com.depromeet.plzdisturb.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.depromeet.plzdisturb.R;
import com.depromeet.plzdisturb.model.User;

import java.util.ArrayList;
import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_MEMBER = 0;
    private static final int ITEM_INVITATION = 1;

    List<User> memberList;

    @Override
    public int getItemViewType(int position) {
        return position == memberList.size() ? ITEM_INVITATION : ITEM_MEMBER;
    }

    public MemberAdapter() {
        memberList = new ArrayList<>();
    }

    public void setMembers(List<User> memberList) {
        this.memberList = memberList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (viewType == ITEM_INVITATION) {
            return new InvitationViewHolder(inflater.inflate(R.layout.item_invitation, parent, false));
        }

        return new MemberViewHolder(inflater.inflate(R.layout.item_member, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InvitationViewHolder) {
            return;
        }

        ((MemberViewHolder) holder).bind(memberList.get(position));
    }

    @Override
    public int getItemCount() {
        return memberList.size() + 1;
    }

    class MemberViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivProfile;
        private final TextView tvName;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.item_member_profile_iv);
            tvName = itemView.findViewById(R.id.item_member_name_tv);
        }

        public void bind(User member) {
            Glide.with(ivProfile)
                    .load(member.getProfileUrl())
                    .circleCrop()
                    .into(ivProfile);

            tvName.setText(member.getName());
        }

    }

    class InvitationViewHolder extends RecyclerView.ViewHolder {

        public InvitationViewHolder(@NonNull View itemView) {
            super(itemView);
            ((RoomActivity) itemView.getContext()).invite();
        }

    }
}
