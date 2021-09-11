# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.

project(
    pazusoba_android
    LANGUAGES CXX
)

# include pazusoba
add_subdirectory(pazusoba)

# pthread is required for multithreading
set(THREADS_PREFER_PTHREAD_FLAG ON)
find_package(Threads REQUIRED)
link_libraries(Threads::Threads)

# include all headers
link_libraries(fmt::fmt-header-only)
include_directories(pazusoba/include)

# all source files
file(GLOB_RECURSE PAZUSOBA_SOURCES "pazusoba/src/*.cpp")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_compile_options(-g)
add_library(
    pazusoba_android
    SHARED
    pazusoba_android.cpp ${PAZUSOBA_SOURCES}
)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library(
    extra-libs
    # add other libraries from NDK
    log
)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries(
    pazusoba_android
    ${extra-libs}
)