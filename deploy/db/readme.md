# Docker 를 이용한 MySQL 설치

## Docker File을 이용한 MySql 설치 방법

이미지 빌드
$ docker build . -t local_mysql_image -f Dockerfile_local_mysql

이미지 확인
$ docker images

컨테이너 실행
$ docker run -p 3306:3306 -d --name kingpiggy_local_mysql local_mysql_image

컨테이너 확인
$ docker ps -a

## Docker Compose File을 이용한 MySql 설치 방법

컨테이너 생성 및 실행
$ docker-compose -f docker_compose_local_mysql.yml up -d

컨테이너 실행
$ docker start local_mysql