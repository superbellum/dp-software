FROM python:3.12
COPY . .
EXPOSE 50053
RUN apt-get -qq update && apt install -y libgl1
RUN pip3 install -r requirements.txt
CMD [ "python3", "main.py" ]