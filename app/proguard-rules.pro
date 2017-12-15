# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/emmar/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

##---------------Begin: proguard configuration for BUTTERKNIFE  ----------
-dontwarn butterknife.internal.**
-keepnames class * { @butterknife.Bind *;}
-keep class **$$ViewBinder { *; }

# Retain generated class which implement Unbinder.
-keep public class * implements butterknife.Unbinder { public <init>(...); }

# Prevent obfuscation of types which use ButterKnife annotations since the simple name
# is used to reflectively look up the generated ViewBinding.
-keep class butterknife.** { *; }
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }
##---------------End: proguard configuration for BUTTERKNIFE  ----------

##---------------Begin: proguard configuration for OKIO  ----------
-dontwarn okio.**
##---------------End: proguard configuration for OKIO  ----------

##---------------Begin: proguard configuration for NETMERA  ----------
-keep class com.netmera.** { *; }
##---------------End: proguard configuration for NETMERA  ----------

##---------------Begin: proguard configuration for EVENTBUS  ----------
-keepclassmembers class ** { public void onEvent*(**); }
##---------------End: proguard configuration for EVENTBUS  ----------

##---------------Begin: proguard configuration for GSON  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

##---------------End: proguard configuration for GSON  ----------