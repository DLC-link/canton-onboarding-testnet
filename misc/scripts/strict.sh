#!/usr/bin/env bash

enable_strict_mode() {
  set -e
  set -u
  if (set -o pipefail) 2>/dev/null; then
    set -o pipefail
  fi
}