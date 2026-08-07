local lib = import 'library.libsonnet';
local data = importstr 'data.txt';

{
  config: lib.defaultConfig,
  rawData: data
}
