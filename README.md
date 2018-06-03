
# Android Cache Manager

Android library to simplify caching of strings, JSON objects, JSON arrays and Bitmap images.

### Setup
```gradle
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```

Add the following dependency in your app/build.gradle file:
```gradle
    implementation 'org.bitbucket.ashwin_dinesh:android_cache_manager:1.0.1'
```

### Usage

#### Caching strings
```gradle
    // save simple text in 'test.txt' file
    CacheManager.save(applicationContext, Store.FILE, "test.txt", "WooHoo! this is cached text")

    // retrieve the cached text from 'test.txt' file
    var content = CacheManager.get(applicationContext, Store.FILE, "test.txt")
```

#### Caching JSON objects
```gradle
    // save json object
    CacheManager.save(applicationContext, Store.FILE, "json_object.txt", jsonObject)

    // retrieve the cached JSON object from 'json_object.txt' file
    val jsonObject = CacheManager.getJsonObject(applicationContext, Store.FILE, "json_object.txt")
```

#### Caching JSON array
```gradle
    // save json array
    CacheManager.save(applicationContext, Store.FILE, "json_array.txt", jsonArray)

    // retrieve the cached JSON array from 'json_array.txt' file
    val jsonArray = CacheManager.getJsonArray(applicationContext, Store.FILE, "json_array.txt")
```

#### Caching Bitmaps
```gradle
    // save bitmap to CacheManager
    CacheManager.save(applicationContext, Store.FILE, "bitmap_image.png", bitmap)

    // retrieve the bitmap from CacheManager
    val cachedBitmap = CacheManager.getBitmap(applicationContext, Store.FILE, "bitmap_image.png")
```

#### Deleting cached file
```gradle
    // delete a cached file
    CacheManager.delete(applicationContext, Store.FILE, "test.txt")
```
