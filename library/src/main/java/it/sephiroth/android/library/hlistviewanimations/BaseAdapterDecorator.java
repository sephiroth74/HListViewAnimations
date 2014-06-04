/*
 * Copyright 2013 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.sephiroth.android.library.hlistviewanimations;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;

import it.sephiroth.android.library.widget.AbsHListView;

/**
 * A decorator class that enables decoration of an instance of the BaseAdapter
 * class.
 * <p/>
 * Classes extending this class can override methods and provide extra
 * functionality before or after calling the super method.
 */
public abstract class BaseAdapterDecorator extends BaseAdapter implements SectionIndexer, HListViewSetter {

	protected final BaseAdapter mDecoratedBaseAdapter;

	protected AbsHListView mListView;

	protected boolean mIsParentHorizontalScrollContainer;

	public BaseAdapterDecorator(final BaseAdapter baseAdapter) {
		mDecoratedBaseAdapter = baseAdapter;
	}

	@Override
	public void setAbsHListView(final AbsHListView listView) {
		mListView = listView;

		if (mDecoratedBaseAdapter instanceof HListViewSetter) {
			((HListViewSetter) mDecoratedBaseAdapter).setAbsHListView(listView);
		}
	}

	public AbsHListView getAbsHListView() {
		return mListView;
	}

	@Override
	public int getCount() {
		return mDecoratedBaseAdapter.getCount();
	}

	@Override
	public Object getItem(final int position) {
		return mDecoratedBaseAdapter.getItem(position);
	}

	@Override
	public long getItemId(final int position) {
		return mDecoratedBaseAdapter.getItemId(position);
	}

	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		return mDecoratedBaseAdapter.getView(position, convertView, parent);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return mDecoratedBaseAdapter.areAllItemsEnabled();
	}

	@Override
	public View getDropDownView(final int position, final View convertView, final ViewGroup parent) {
		return mDecoratedBaseAdapter.getDropDownView(position, convertView, parent);
	}

	@Override
	public int getItemViewType(final int position) {
		return mDecoratedBaseAdapter.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		return mDecoratedBaseAdapter.getViewTypeCount();
	}

	@Override
	public boolean hasStableIds() {
		return mDecoratedBaseAdapter.hasStableIds();
	}

	@Override
	public boolean isEmpty() {
		return mDecoratedBaseAdapter.isEmpty();
	}

	@Override
	public boolean isEnabled(final int position) {
		return mDecoratedBaseAdapter.isEnabled(position);
	}

	@Override
	public void notifyDataSetChanged() {
		mDecoratedBaseAdapter.notifyDataSetChanged();
	}

	/**
	 * Helper function if you want to force notifyDataSetChanged()
	 */
	@SuppressWarnings ("UnusedDeclaration")
	public void notifyDataSetChanged(final boolean force) {
		if (force) {
			mDecoratedBaseAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void notifyDataSetInvalidated() {
		mDecoratedBaseAdapter.notifyDataSetInvalidated();
	}

	@Override
	public void registerDataSetObserver(final DataSetObserver observer) {
		mDecoratedBaseAdapter.registerDataSetObserver(observer);
	}

	@Override
	public void unregisterDataSetObserver(final DataSetObserver observer) {
		mDecoratedBaseAdapter.unregisterDataSetObserver(observer);
	}

	@Override
	public int getPositionForSection(final int section) {
		if (mDecoratedBaseAdapter instanceof SectionIndexer) {
			return ((SectionIndexer) mDecoratedBaseAdapter).getPositionForSection(section);
		}
		return 0;
	}

	@Override
	public int getSectionForPosition(final int position) {
		if (mDecoratedBaseAdapter instanceof SectionIndexer) {
			return ((SectionIndexer) mDecoratedBaseAdapter).getSectionForPosition(position);
		}
		return 0;
	}

	@Override
	public Object[] getSections() {
		if (mDecoratedBaseAdapter instanceof SectionIndexer) {
			return ((SectionIndexer) mDecoratedBaseAdapter).getSections();
		}
		return null;
	}

	public BaseAdapter getDecoratedBaseAdapter() {
		return mDecoratedBaseAdapter;
	}

	/**
	 * If the adapter's list-view is hosted inside a parent(/grand-parent/etc) that can scroll horizontally,
	 * horizontal swipes won't
	 * work, because the parent will prevent touch-events from reaching the list-view.
	 * <p/>
	 * Call this method with the value 'true' to fix this behavior.
	 * Note that this will prevent the parent from scrolling horizontally when the user touches anywhere in a list-item.
	 */
	public void setIsParentHorizontalScrollContainer(final boolean isParentHorizontalScrollContainer) {
		mIsParentHorizontalScrollContainer = isParentHorizontalScrollContainer;
	}

	public boolean isParentHorizontalScrollContainer() {
		return mIsParentHorizontalScrollContainer;
	}
}