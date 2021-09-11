#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_org_github_henryquan_pazusoba_1android_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_org_github_henryquan_pazusoba_1android_MainActivity_helloworldFromJNI(
        JNIEnv* env,
jobject /* this */) {
std::string hello = "Hello World from C++!";
return env->NewStringUTF(hello.c_str());
}