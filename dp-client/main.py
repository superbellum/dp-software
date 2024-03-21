import base64

data = open('../matcher/database/100__M_Left_index_finger_CR.BMP', 'rb').read()
data_base64 = base64.b64encode(data)
data_base64_str = data_base64.decode()

with open('../matcher/database/100__M_Left_index_finger_CR_encoded.txt', 'w') as out_file:
    out_file.write(data_base64_str)
