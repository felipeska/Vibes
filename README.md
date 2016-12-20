# Vibes
Radial propagation background for Android

## Usage

```java
new Vibes.Builder()
    .waves(4)
    .stroke(10)
    .start(40)
    .time(700)
    .color(Color.CYAN)
    .into(imageView1);
```

Download
============

RxNearby is ready to be used via [jitpack.io](https://jitpack.io/#hugeinc/Vibes).
Simply add the following code to your root `build.gradle`:

```groovy
allprojects {
repositories {
jcenter()
maven { url "https://jitpack.io" }
}
}
```

Now add the gradle dependency in your application's `build.gradle`:

```groovy
dependencies {
compile 'com.github.hugeinc:Vibes:0.0.1'
}
```

## LICENSE

Copyright 2016 Huge, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

<http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
