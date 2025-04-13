export const onFileSelected = (event: Event, onLoad: (result: string) => void, onError: (error: any) => void) => {
  const input = event.target as HTMLInputElement;
  if (!input.files || input.files.length === 0) {
    return;
  }

  const file = input.files[0];
  const reader = new FileReader();
  reader.readAsDataURL(file);
  reader.onload = () => {
    onLoad(reader.result as string);
  };
  reader.onerror = onError;
}
