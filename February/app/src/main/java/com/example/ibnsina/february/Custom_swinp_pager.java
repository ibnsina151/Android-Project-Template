package com.example.ibnsina.february;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ibnsina on 2/16/17.
 */

public class Custom_swinp_pager extends PagerAdapter {

    private int[] images_resources = {R.drawable.gallery_1,R.drawable.gallery_2,R.drawable.gallery_3,R.drawable.gallery_4,R.drawable.gallery_5,
            R.drawable.gallery_6,R.drawable.gallery_7,R.drawable.gallery_8,R.drawable.gallery_9,R.drawable.gallery_10,R.drawable.gallery_11,};
    private Context ctx;

    private LayoutInflater layoutInflater;

    public Custom_swinp_pager(Context ctx)
    {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return images_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swaping_layout,container,false);
        ImageView imageView = (ImageView)item_view.findViewById(R.id.image_view);
        TextView textView = (TextView)item_view.findViewById(R.id.image_count);
        imageView.setImageResource(images_resources[position]);

        textView.setText("image"+position);


        TextView gallery_content = (TextView)item_view.findViewById(R.id.gallary_content);

        switch(position)
        {
            case 0: gallery_content.setText("রাষ্ট্রভাষা হিসেবে বাংলার অধিকার আদায়ে ১৯৫২ সালের ২১ ফেব্রুয়ারিতে ঢাকায় আয়োজিত মিছিল।");
                break;
            case 1: gallery_content.setText("১৯৪৮ সালের ২১শে মার্চ মুহাম্মদ আলী জিন্নাহ্ ঢাকায় এক ভাষণে ঘোষণা করেন “উর্দুই হবে পাকিস্তানের একমাত্র রাষ্ট্রভাষা“।");
                break;
            case 2: gallery_content.setText("১৯৫২ সালের ৪ ফেব্রুয়ারি নওয়াবপুর জগন্নাথ বিশ্ববিদ্যালয়ের (জবি) (তৎকালীন জগন্নাথ কলেজ) ছাত্রদের মিছিল।");
                break;
            case 3: gallery_content.setText("২১শে ফেব্রুয়ারি ১৯৫২: ১৪৪ ধারা ভঙ্গের প্রশ্নে পুরাতন কলাভবন প্রাঙ্গণে আমতলায় ঐতিহাসিক ছাত্রসভা।");
                break;
            case 4: gallery_content.setText("২১শে ফেব্রুয়ারি ১৯৫২: পুরাতন কলাভবন প্রাঙ্গণ, ১৪৪ ধারা ভঙ্গের প্রাক্কালে।");
                break;
            case 5: gallery_content.setText("২২ ফেব্রুয়ারি ঢাকা মেডিক্যাল কলেজ প্রাঙ্গণে জানাজা শেষে বিশাল মিছিল বের হয়।");
                break;
            case 6: gallery_content.setText("আবুল বরকতের পরিবার শহীদ মিনারের ভিত্তি প্রস্তর স্থাপন করে।");
                break;
            case 7: gallery_content.setText("১৯৫৩ সালের ২১শে ফেব্রুয়ারি প্রভাতফেরিতে ফ্যাস্টুন হাতে ঢাকা বিশ্ববিদ্যালয়ের ছাত্রীরা।");
                break;
            case 8: gallery_content.setText("১৯৫৩ সালের ২১শে ফেব্রুয়ারি: প্রথম শহীদ দিবস সকালে ছাত্র-জনতার শোক শোভাযাত্রা মেডিক্যাল হোস্টেল মোড়ে (যেখান থেকে গুলি চলেছিল) শহীদানের আত্মার মাগফেরাত কামনায় মোনাজাত করছে।");
                break;
            case 9: gallery_content.setText("১৯৫৪ সালের ২১ ফেব্রুয়ারি।");
                break;
            case 10: gallery_content.setText("কেন্দ্রীয় শহীদ মিনার; ভাষা আন্দোলনে শহীদদের স্মৃতির প্রতি শ্রদ্ধা জানিয়ে ঢাকা মেডিক্যাল কলেজের পাশে নির্মিত।");
                break;
        }

        container.addView(item_view);

        return item_view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
