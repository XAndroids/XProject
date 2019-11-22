package workshop1024.com.xproject.login.controller.native

import org.junit.Assert.assertEquals
import org.junit.Test
import workshop1024.com.xproject.login.native.NativeLib
import java.io.File

class NativeLibTest {
    @Test
    fun test_stringFromJNI() {
        //java.lang.UnsatisfiedLinkError: Directory separator should not appear in library name
        //no suitable image found.  Did find: libnative-lib.dylib: file too short
        //System.load，参数必须是绝对路劲
        //参考：System.load 和 System.loadLibrary区别，https://blog.csdn.net/wwlwwy89/article/details/41147413
        System.load("/Users/qitmac0000562/XCodeProjects/Xs/XProject/login/build/dylibs/libnative-lib.dylib")
        val nativeLib = NativeLib()
        assertEquals("Hello from C++", nativeLib.stringFromJNI())
    }
}