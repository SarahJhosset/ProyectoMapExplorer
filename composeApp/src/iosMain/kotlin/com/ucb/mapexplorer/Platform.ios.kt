package com.ucb.mapexplorer

import platform.UIKit.UIDevice
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.Foundation.NSUserDefaults

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getSystemLanguageCode(): String = NSLocale.currentLocale.languageCode

actual fun saveLanguageSetting(code: String) {
    NSUserDefaults.standardUserDefaults.setObject(code, "language_code")
}

actual fun getLanguageSetting(): String? {
    return NSUserDefaults.standardUserDefaults.stringForKey("language_code")
}
