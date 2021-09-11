#include <jni.h>
#include <string>
#include <pazusoba/core.h>

/// Convert jstring to std::string
std::string convertJString(JNIEnv *env, jstring javaString) {
    auto length = env->GetStringUTFLength(javaString);
    auto charPointer = env->GetStringUTFChars(javaString, nullptr);
    std::string nativeString(charPointer, length);
    // Free up the pointer
    env->ReleaseStringUTFChars(javaString, charPointer);
    return nativeString;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_org_github_henryquan_pazusoba_1android_core_Pazusoba_solve(
        JNIEnv *env, jobject,
        jstring board, jint min_erase,
        jint max_step, jint beam_size
) {
    pazusoba::Timer t("=> pazusoba");
    auto board_string = convertJString(env, board);
    auto parser = pazusoba::Parser(board_string, min_erase, max_step, beam_size);
    parser.parse();

    auto solver = pazusoba::BeamSearch(parser);
    auto route = solver.solve();
    auto board_string_exported = parser.board().getFormattedBoard(pazusoba::dawnglare);
    return env->NewStringUTF(board_string_exported.c_str());
}

