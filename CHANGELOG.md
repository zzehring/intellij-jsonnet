<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# intellij-jsonnet Changelog

## [0.0.4]
### Changed
- handle stopping/starting server after download correctly.

## [0.0.3]
### Fixed
- stop servers before writing binary to avoid `file busy` errors
- correct semver compare logic for determining whether to update language server
- remove unsupported annotation to fix compatibility issues with other Jetbrains IDEs (e.g. Goland)

## [0.0.2]
### Fixed
- Fixed compatibility by narrowing scope (requires newer version of IDEs)

## [0.0.1]
### Added
- Initial scaffold created from [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template)