#!/bin/bash
pwd
cd /Users/alex/Desktop/ios_project/mobike_ios
xcodebuild -scheme Mobike archive -archivePath ./build/Mobike.xcarchive
xcodebuild -exportArchive -archivePath ./build/Mobike.xcarchive  -exportPath ./build/  -exportOptionsPlist ./autoBuild/auto.plist



