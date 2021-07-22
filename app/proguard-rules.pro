# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name handle the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this handle preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this handle
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keeps SerializedName GSON annotation, because it may crash network calls
# and throw BadServerResponse error.
-keepclassmembers,allowobfuscation class * {
    @com.google.gson.annotations.SerializedName <fields>;
  }
-keep,allowobfuscation interface com.google.gson.annotations.SerializedName

# Keeps enums for GSON, because app may crash with NoSuchFieldException
# as GSON cannot find them.
-keepclassmembers enum * { *; }