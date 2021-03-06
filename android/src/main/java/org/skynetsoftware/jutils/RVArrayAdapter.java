package org.skynetsoftware.jutils;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public abstract class RVArrayAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    @NonNull
    protected final Context context;

    @NonNull
    List<T> items;

    @Nullable
    public OnItemClickListener onItemClickListener;

    public RVArrayAdapter(@NonNull Context context, @NonNull List<T> items)
    {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public boolean isEmpty()
    {
        return items.isEmpty();
    }

    public void clear()
    {
        items.clear();
        notifyDataSetChanged();
    }

    public T getItem(int position)
    {
        return items.get(position);
    }

    public void add(boolean clear, T... s)
    {
        addAll(Arrays.asList(s), clear);
    }

    public void add(T... s)
    {
        add(false, s);
    }

    public void addAll(List<T> list, boolean clear)
    {
        if (clear) items.clear();
        items.addAll(list);
        notifyDataSetChanged();
    }

    public void add(int position, T item)
    {
        items.add(position, item);
        notifyDataSetChanged();
    }

    public void addAll(List<T> list, int index)
    {
        items.addAll(index, list);
    }

    public void remove(T item)
    {
        items.remove(item);
    }

    public void remove(int position)
    {
        items.remove(position);
    }

    public OnItemClickListener getOnItemClickListener()
    {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, Object item, int position);
    }
}