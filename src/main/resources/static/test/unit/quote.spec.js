import {Quote} from '../../src/quotes/quote';

describe('a default quote', () => {
  let defaultQuote = new Quote("Lion-O", "Thunder!");
  
  it('can be jsonified', () => {
    expect(defaultQuote.json()).toBe({
      "lines": [
        {
          "text": "Thunder!",
          "punchLine": false,
          "lineType": "SPEECH",
          "participants": [
            {
              "name": "Lion-O",
              "victim": false
            }
          ]
        }
      ]
    });
  });
});
