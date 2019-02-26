#!/usr/bin/env bash

git add -f ./src/main/resources
git add -A
eb deploy --profile pream-eb --staged
git reset HEAD ./src/main/resources