# AndroidBaseProject
> Base template to start work with next project without hardcode

[![Release](https://jitpack.io/v/androidovshchik/BaseProject.svg)](https://jitpack.io/#androidovshchik/BaseProject)
<a href="https://www.paypal.me/mrcpp" title="Donate to this project using Paypal">
    <img src="https://img.shields.io/badge/paypal-donate-green.svg" alt="PayPal donate button"/>
</a>

## Documentation

[core module][1]

[core module short][50]

[support module][2]

[support module short][51]

[sqlite module][3]

[sqlite module short][52]

## Download

Add to your build.gradle:
```gradle
allprojects {
    repositories {
        maven {
            url "https://jitpack.io"
        }
    }
}
```
and:

```gradle
dependencies {
    implementation 'com.github.androidovshchik.BaseProject:core:LATEST_VERSION'
    implementation 'com.github.androidovshchik.BaseProject:support:LATEST_VERSION'
    implementation 'com.github.androidovshchik.BaseProject:sqlite:LATEST_VERSION'
}
```

Or

Clone [this repository](https://github.com/androidovshchik/AndroidBlankProject)

[1]: https://androidovshchik.github.io/AndroidBaseProject/core/index.html
[2]: https://androidovshchik.github.io/AndroidBaseProject/support/index.html
[3]: https://androidovshchik.github.io/AndroidBaseProject/sqlite/index.html
[50]: https://androidovshchik.github.io/AndroidBaseProject/core/index-outline.html
[51]: https://androidovshchik.github.io/AndroidBaseProject/support/index-outline.html
[52]: https://androidovshchik.github.io/AndroidBaseProject/sqlite/index-outline.html