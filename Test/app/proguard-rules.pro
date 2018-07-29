
#Jackson START
-keepnames class com.fasterxml.jackson.** { *; }
-keepnames class cz.airbank.mb.android.service.helpers.jackson.** { *; }
-dontwarn com.fasterxml.jackson.databind.**
-keep class cz.airbank.mb.android.notifications.detail.data.** { *; }
# Jackson END

# PocketKnife START
-keep class pocketknife.** { *; }
-dontwarn pocketknife.internal.**
-keep class **$$BundleAdapter { *; }
-keep class **$$IntentAdapter { *; }

-keepclasseswithmembernames class * {
    @pocketknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @pocketknife.* <methods>;
}
# PocketKnife END

# Retrolambda START
-dontwarn java.lang.invoke.*
# Retrolambda END

# Parceler START
-keep interface org.parceler.Parcel
-keep @org.parceler.Parcel class * { *; }
-keep class **$$Parcelable { *; }
# Parceler END

# Glide START
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# Glide END

# OkHttp START
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
# OkHttp END

# Dagger 2 START
-dontwarn com.google.errorprone.annotations.**
# Dagger 2 END