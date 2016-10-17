let hotkeymap = {
  submitQuote: { key:'ctrl+Enter', predicate: (event) => (event.ctrlKey || event.metaKey) && event.key === 'Enter' },
  nextLine: { key:'Enter', predicate: (event) => event.key === 'Enter' }
};

export class Hotkeys {

  submitQuoteKeyPressed(event) {
    return hotkeymap.submitQuote.predicate(event);
  }
  
  nextLineKeyPressed(event) {
    return hotkeymap.nextLine.predicate(event);
  }
}
