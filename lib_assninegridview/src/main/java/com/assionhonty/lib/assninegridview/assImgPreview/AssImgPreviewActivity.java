package com.assionhonty.lib.assninegridview.assImgPreview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.assionhonty.lib.assninegridview.AssNineGridView;
import com.assionhonty.lib.assninegridview.ImageInfo;
import com.assionhonty.lib.assninegridview.R;
import com.hjq.permissions.OnPermission;

import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @author assionhonty
 * Created on 2018/9/19 9:41.
 * Email：assionhonty@126.com
 * Function:图片预览页面
 */
public class AssImgPreviewActivity extends AppCompatActivity {

    private List<ImageInfo> imageInfos;
    private int currentIndex;

    private TextView tvNum;
    private Button btnSavePic;
    Bitmap bitmap;
    List<String> allpic = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ass_img_preview_activity);


        Intent intent = getIntent();
        imageInfos = (List<ImageInfo>) intent.getSerializableExtra("imageInfo");
        currentIndex = intent.getIntExtra("currentIndex",0);

        AssViewPager vp = findViewById(R.id.vp);
        tvNum = findViewById(R.id.tv_num);
        btnSavePic = findViewById(R.id.btn_savepic);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this);
        vp.setAdapter(myPagerAdapter);
        vp.setOffscreenPageLimit(4);
        vp.setCurrentItem(currentIndex);
        tvNum.setText((currentIndex + 1) + " / " + imageInfos.size());


        for(int i = 0; i < imageInfos.size(); i++) {
            allpic.add(imageInfos.get(i).getBigImageUrl());
        }

        //点击保存图片
        btnSavePic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        returnBitMap(vp.getCurrentItem(),new OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                });
                //Toast.makeText(AssImgPreviewActivity.this,"点击啦"+vp.getCurrentItem(), Toast.LENGTH_SHORT).show();
            }
        });


        vp.addOnPageChangeListener(new AssViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position,float positionOffset,int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                tvNum.setText((position + 1) + " / " + imageInfos.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyPagerAdapter extends PagerAdapter implements PhotoViewAttacher.OnPhotoTapListener {
        private Context mContext;

        MyPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return imageInfos.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view,@NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container,int position) {
            View view = View.inflate(mContext,R.layout.ass_img_preview_item,null);
            final ProgressBar pb = view.findViewById(R.id.pb);
            final PhotoView imageView = view.findViewById(R.id.pv);

            ImageInfo info = imageInfos.get(position);
            imageView.setOnPhotoTapListener(this);
            showExcessPic(info,imageView);

            //如果需要加载的loading,需要自己改写,不能使用这个方法
            AssNineGridView.getImageLoader().onDisplayImage(view.getContext(),imageView,info.bigImageUrl);

//        pb.setVisibility(View.VISIBLE);
//        Glide.with(context).load(info.bigImageUrl)//
//                .placeholder(R.drawable.ic_default_image)//
//                .error(R.drawable.ic_default_image)//
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//
//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        pb.setVisibility(View.GONE);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        pb.setVisibility(View.GONE);
//                        return false;
//                    }
//                }).into(imageView);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container,int position,@NonNull Object object) {
            // super.destroyItem(container,position,object); 这一句要删除，否则报错
            container.removeView((View) object);
        }

        @Override
        public void onPhotoTap(View view,float x,float y) {
            finish();
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }
    }

    /**
     * 展示过度图片
     */
    private void showExcessPic(ImageInfo imageInfo,PhotoView imageView) {
        //先获取大图的缓存图片
        Bitmap cacheImage = AssNineGridView.getImageLoader().getCacheImage(imageInfo.bigImageUrl);
        //如果大图的缓存不存在,在获取小图的缓存
        if(cacheImage == null) {
            cacheImage = AssNineGridView.getImageLoader().getCacheImage(imageInfo.thumbnailUrl);
        }
        //如果没有任何缓存,使用默认图片,否者使用缓存
        if(cacheImage == null) {
            imageView.setImageResource(R.drawable.ic_default_color);
        } else {
            imageView.setImageBitmap(cacheImage);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

    }

    /**
     * 请求读取sd卡的权限
     */
    private void requestPermission(final OnClickListener call) {
        XXPermissions.with(AssImgPreviewActivity.this)
                .constantRequest()
                .permission(Permission.Group.STORAGE)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted,boolean isAll) {
                        if(isAll) {
                            call.onClick(null);
                        } else {
                            Toast.makeText(AssImgPreviewActivity.this,"获取权限成功，部分权限未正常授予",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied,boolean quick) {
                        if(quick) {
                            Toast.makeText(AssImgPreviewActivity.this,"被永久拒绝授权，请手动授予权限",Toast.LENGTH_SHORT).show();
                            //ToastUtils.show("被永久拒绝授权，请手动授予权限");
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            //XXPermissions.gotoPermissionSettings(AssImgPreviewActivity.this);
                            XXPermissions.startPermissionActivity(AssImgPreviewActivity.this, denied);
                        } else {
                            Toast.makeText(AssImgPreviewActivity.this,"获取权限失败",Toast.LENGTH_SHORT).show();
                            //ToastUtils.show("获取权限失败");
                        }
                    }
                });
    }

    /**
     * 描述：下载网络图片
     * 作者：Hujm
     * 添加版本：V4.2.12
     * 时间：2020/3/23 17:02
     * -------------------------------
     */
    public void returnBitMap(int posi,OnClickListener view) {
        new Thread(() -> {
            //for(int i = 0; i < allpic.size(); i++) {
            URL imageurl = null;
            try {
                imageurl = new URL(imageInfos.get(posi).getBigImageUrl());
                HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                conn.setDoInput(true);
                conn.setConnectTimeout(60000);
                conn.setReadTimeout(60000);
                conn.connect();
                InputStream is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                //保存图片
                saveImageToGallery(bitmap);
                is.close();
            } catch(MalformedURLException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            }
            //}
            view.onClick(null);
        }).start();
    }

    /**
     * 描述：图片存储到本地
     * 作者：Hujm
     * 添加版本：V4.2.12
     * 时间：2020/3/23 17:02
     * -------------------------------
     */
    public File saveImageToGallery(Bitmap bmp) {
        if(bmp == null) {
            Log.e("cc","bitmap---为空");
            return null;
        }
//        String galleryPath = Environment.getExternalStorageDirectory()
//                + File.separator + Environment.DIRECTORY_DCIM
//                + File.separator + "Camera" + File.separator;
        String fileName = +System.currentTimeMillis() + ".jpg";
        File file = new File(Environment.getExternalStorageDirectory(),fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG,60,fos);
            fos.flush();
            fos.close();
            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            AssImgPreviewActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri));
            if(isSuccess) {
                Log.e("cc","图片保存成功 保存在:" + file.getPath());
                //ToastUtils.show("图片保存成功 保存在:" + file.getPath());
                ToastUtils.show("图片保存成功");
            } else {
                Log.e("cc","图片保存失败");
                //ToastUtils.show("失败");
                ToastUtils.show("图片保存失败");
            }
        } catch(IOException e) {
            Log.e("cc","保存图片找不到文件夹");
            e.printStackTrace();
        }
        return file;
    }

}
