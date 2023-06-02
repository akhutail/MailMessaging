import uuidTime from 'uuid-time'



export const getTimeOrDate = (timeUUID) => {
    const date = new Date(0);
    date.setUTCMilliseconds(uuidTime.v1(timeUUID));
    const today = new Date();
    if (
        today.getFullYear() === date.getFullYear() &&
        today.getMonth() === date.getMonth() &&
        today.getDate() === date.getDate()
    ) {
        const options = { hour: '2-digit', minute: '2-digit' };

        return date.toLocaleTimeString(undefined, options);

    }
    else {
        const options = { day: 'numeric', month: 'short', year: '2-digit' };
        return date.toLocaleDateString([], options);
    }
}