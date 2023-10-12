from flask import Flask,request,jsonify

app = Flask(__name__)


@app.route('/orders/ai', methods=['POST'])
def run_ai():
    input_json = request.get_json(force=True)
    # force=True, above, is necessary if another developer
    # forgot to set the MIME type to 'application/json'
    print('data from client:', input_json)
    dictToReturn = {'answer': 42}
    return jsonify(dictToReturn)


if __name__ == '__main__':
    app.run()
