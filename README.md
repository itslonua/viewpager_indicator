[![](https://jitpack.io/v/itslonua/viewpager_indicator.svg)](https://jitpack.io/#itslonua/viewpager_indicator)
# Viewpager Indicator ( Kotlin version )
Simple indicator with a vertical streaks
This indicator could change state on partial view pager offset
![image](https://user-images.githubusercontent.com/10744009/57377926-4a842a80-71ac-11e9-8016-693288223840.png)

# Usage:
        //set ViewPager Adapter to View Pager.
        view_pager.adapter = SectionsPagerAdapter(this, supportFragmentManager)

        //set ViewPager to indicator
        view_pager_indicator.setupWithViewPager(view_pager)

        //set indicator size
        view_pager_indicator.setupIndicatorSize(3)
        
# Custom attributes:
        //This attributes used to change gap between indicator streaks
        app:start_margin="@dimen/indicator_gap_dp"
        app:end_margin="@dimen/indicator_gap_dp"

# JitPack:
Add it in your root build.gradle at the end of repositories:
       
       allprojects {
         repositories {
          ...
          maven { url 'https://jitpack.io' }
         }
        }
        
Add the dependency

       dependencies {
                implementation 'com.github.itslonua:viewpager_indicator:1.0.0'
        }
