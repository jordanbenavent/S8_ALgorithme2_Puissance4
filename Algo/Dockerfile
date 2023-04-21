FROM python:3.9

WORKDIR /app

COPY requirements.txt .
COPY main.py .
COPY benchmark.py .
COPY convertStringToTab.py .
COPY pythonPuissance.py .

RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 5000

CMD ["python", "main.py"]
