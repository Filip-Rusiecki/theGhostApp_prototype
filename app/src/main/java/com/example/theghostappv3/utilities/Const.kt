package com.example.theghostappv3.utilities

object Const {

    const val CAMERA_PERMISSION_CODE = 100
    const val TAG = "CameraActivity"
    const val FILE_NAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    const val REQUEST_CODE_PERMISSIONS = 10
    const val REQUEST_CODE_PHOTO = 20
    const val REQUEST_CODE_VIDEO = 30
    const val REQUEST_CODE_AUDIO = 40
    const val REQUEST_CODE_GALLERY = 50
    const val REQUEST_CODE_SETTINGS = 60
    const val REQUEST_CODE_PERMISSIONS_SETTINGS = 70
    const val FILE_NAME = "photo.jpg"

    const val ACCESSFINELOCATION = 100
    const val ACCESSCOARSELOCATION = 101
    const val ACCESSBACKGROUNDLOCATION = 102

    const val LOCATION_PERMISSION_REQUEST_CODE = 1
    const val REQUEST_CHECK_SETTINGS = 2

    const val VIDEO_FILE_NAME = "video.mp4"
    const val AUDIO_FILE_NAME = "audio.mp3"

    val REQUEST_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)

}