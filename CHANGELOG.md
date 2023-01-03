<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# intellij-jsonnet Changelog

## [Unreleased]
### Added
- Allow compatibility with 223.* IDE versions

## [0.0.9]
### Added
- Comment support

### Fixed
- Allow compatibility with 222.* IDE versions

## [0.0.7]
### Added
- Implement Jsonnet reformatting

## [0.0.6]
### Fixed
- Fix setting file permissions for non-POSIX systems

## [0.0.5]
### Added
- Brace match highlighting

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