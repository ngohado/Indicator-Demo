# Indicator View

This indicator library is made by me and i'm so happy if you use it to learn or immigrate to your project.

My code in library so clear and simply to understand. I hope you can learn something in it.

This is my guide article, it show you how to build this library (only Vietnamese version): [https://kipalog.com/posts/Android--Hieu-sau-hon-ve-CustomView-va-Huong-dan-xay-dung-thu-vien-UI-IndicatorView](https://kipalog.com/posts/Android--Hieu-sau-hon-ve-CustomView-va-Huong-dan-xay-dung-thu-vien-UI-IndicatorView)

## Preview

![](https://media.giphy.com/media/1D5IEz4oliWEE/giphy.gif)

## Immigration
To use it, Download library `zip` > Extract. You simply add some code to your project. First make sure that `indicatorlibrary` module had copied in your project folder, then open `settings.gradle` file:

```
include ..., ':indicatorlibrary'
```

Open `build.gradle` file level app:

```
dependencies {
    ...
    compile project (':indicatorlibrary')
    ...
}
```

Done! Now you can use it for your project.

## Usage

XML:

```
<RelativeLayout>
    ...

    <com.hado.indicatorlibrary.IndicatorView
        android:id="@+id/indicator"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

    ...
</RelativeLayout>
```

Java:

```
  indicatorView.setViewPager(viewPager); //make sure that adapter had added to viewpager
```

## Customization

One of the most important feature of every custom view is ability to customize its look as user need. By calling the following methods (or attributes) you will be able to customize `IndicatorView` as you need.

```
    void setAnimateDuration(long duration);

   /**
    *
    * @param radius: radius in pixel
    */
   void setRadiusSelected(int radius);

   /**
    *
    * @param radius: radius in pixel
    */
   void setRadiusUnselected(int radius);

   /**
    *
    * @param distance: distance in pixel
    */
   void setDistanceDot(int distance);
```

or in XML:

```
<com.hado.indicatorlibrary.IndicatorView
        android:id="@+id/indicator"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_marginBottom="20dp"
        app:hado_radius_selected="10dp"
        app:hado_radius_unselected="5dp" />
```

## Issues

If you encounter with any bug when you use this library, please report it on `Issues` and i will fix it as soon as possible. Thanks for your support.
