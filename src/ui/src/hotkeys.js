let hotkeymap = {
  submitQuote: { key:'ctrl+Enter', predicate: (event) => (event.ctrlKey || event.metaKey) && event.key === 'Enter' },
  nextLine: { key:'Enter', predicate: (event) => event.key === 'Enter' },
  deleteLine: { key:'ctrl+Backspace', predicate: (event) => (event.ctrlKey || event.metaKey) && event.key === 'Backspace' },
  toggleContext: { key:'ctrl+/', predicate: (event) => (event.ctrlKey || event.metaKey) && event.key === '/' }
};

export class Hotkeys {

  submitQuoteKeyPressed(event) {
    return hotkeymap.submitQuote.predicate(event);
  }
  
  nextLineKeyPressed(event) {
    return hotkeymap.nextLine.predicate(event);
  }

  deleteLineKeyPressed(event) {
    return hotkeymap.deleteLine.predicate(event);
  }

  toggleContextKeyPressed(event) {
    return hotkeymap.toggleContext.predicate(event);
  }
}
