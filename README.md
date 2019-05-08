# Viewpager Indicator ( Kotlin version )
Simple indicator with a vertical streaks

![image](https://user-images.githubusercontent.com/10744009/57377796-fa0ccd00-71ab-11e9-97be-1b611a0b0d5f.png)

# Usage:
        //set ViewPager Adapter to View Pager.
        view_pager.adapter = SectionsPagerAdapter(this, supportFragmentManager)

        //set ViewPager to indicator
        view_pager_indicator.setupWithViewPager(view_pager)

        //set indicator size
        view_pager_indicator.setupIndicatorSize(3)
