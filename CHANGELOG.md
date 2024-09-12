<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# intellij-jsonnet Changelog

## Unreleased

## 0.3.1 - 2024-09-12

### Fixed
- Handle possible paths for plugin dir

## 0.3.0 - 2024-08-23

### Added
- Bump gradle version to 8.9
- Migrate to v2 of Gradle IntelliJ Plugin
- Add support for IntelliJ 2024.2

## 0.2.1 - 2024-03-04

### Added
- Allow enabling/disabling of lint and eval diagnostics on language server through plugin settings (PR #80)
- Allow specifying JPaths to pass onto language server (PR #88)

### Changed
- Allow compatibility with 241.* IDE versions (PR #82)
- Update minimum IDE version to 233.2 (PR #82)
- Upgrade kotlin to 1.17.2 (PR #80)

### Fixed
- Correct `jsonnet`/`libsonnet` icon path

## 0.1.6 - 2024-01-03

### Added
- Allow compatibility with 233.* IDE versions
- Upgrade kotlin from 1.7.21 to 1.9.21

## 0.1.5 - 2023-08-02

### Added
- Allow compatibility with 232.* IDE versions

## 0.1.4 - 2023-07-05

### Fixed
- add support for IDE configured http proxy for jsonnet language server binary download (PR #61)

## 0.1.3 - 2023-05-03

### Fixed
- allow for unexpected json fields when querying GitHub for latest releases

## 0.1.2 - 2023-04-10

### Changed
- Allow compatibility with 231.* IDE versions

## 0.1.1 - 2023-03-21

### Fixed
- NullPointerException crashes sometimes when formatting

## 0.1.0 - 2023-01-10

### Changed
- Upgrade dependencies
- Upgrade Java version from 11 to 17
- Update minimum IDE version to 222 (2022.2+)

### Fixed
- Language Server handling (start/update) now working on 223.*

## 0.0.10

### Added
- Allow compatibility with 223.* IDE versions

## 0.0.9

### Added
- Comment support

### Fixed
- Allow compatibility with 222.* IDE versions

## 0.0.7

### Added
- Implement Jsonnet reformatting

## 0.0.6

### Fixed
- Fix setting file permissions for non-POSIX systems

## 0.0.5

### Added
- Brace match highlighting

## 0.0.4

### Changed
- handle stopping/starting server after download correctly.

## 0.0.3

### Fixed
- stop servers before writing binary to avoid `file busy` errors
- correct semver compare logic for determining whether to update language server
- remove unsupported annotation to fix compatibility issues with other Jetbrains IDEs (e.g. Goland)

## 0.0.2

### Fixed
- Fixed compatibility by narrowing scope (requires newer version of IDEs)

## 0.0.1

### Added
- Initial scaffold created from [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template)
