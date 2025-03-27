FROM python:3.12

COPY . .

RUN apt-get -qq update && apt-get -qq install ffmpeg libsm6 libxext6  -y

RUN pip3 install -r requirements.txt

CMD [ "python3", "main.py" ]